<?php
/*

	Generate Authkey and Signature for BCA KlikPay Version 1.4

=== PLEASE READ the following instructions about requirements and how to use ===

Minimum Requirements:
- PHP v5.2.13	=> http://php.net/releases/5_2_13.php
- BCMath		=> http://php.net/manual/en/book.bc.php
- MBString		=> http://php.net/manual/en/book.mbstring.php
- Mcrypt		=> http://php.net/manual/en/book.mcrypt.php

How to use:

<?php

$klikPayCode = "FUS1NDB1SA";
$transactionNo = "AF00042";
$currency = "IDR";
$clearKey = "SomeTopSecretKey";
$transactionDate = "21/06/2012 16:20:04"; // PHP Date Format: d/m/Y H:i:s
$totalAmount = "732500.00";

$bcakey = new KlikPayKeys;
$signature = $bcakey->signature($klikPayCode, $transactionNo, $currency, $clearKey, $transactionDate, $totalAmount);
$authkey = $bcakey->authkey($klikPayCode, $transactionNo, $currency, $transactionDate, $clearKey);
echo $signature;

?>


	Author: Debrian S.A. <rieza.brian@sprintasia.net>
	Copyright 2012 Sprint Asia Technology, PT

Release notes:
v1.0 	08 Aug 2012
- Released v1.0
v1.1	24 Sep 2012
- Adding transactionDate regex match result
v1.2	12 Nov 2012
- Minimum length of transactionNo now is 1 (since it will be padded anyway)
v1.3	15 Jan 2013
- IV for Triple DES now using null-byte (64 bit)
- Re-define output string into english
v1.4	30 Aug 2013
- Concating byte variable for tripledes function
- Using strlen instead of mb_strlen

*/

class KlikPayKeys {
	var $trace = array();
	
	final public function signature($klikPayCode, $transactionNo, $currency = "IDR", $clearKey, $transactionDate, $totalAmount) {
		$this->trace = array(
			"klikPayCode" => $klikPayCode,
			"transactionNo" => $transactionNo,
			"currency" => $currency,
			"transactionDate" => $transactionDate,
			"totalAmount" => $totalAmount
		);
		$klikPayCode = preg_match('/[A-Z0-9a-z]+/',$klikPayCode,$m) ? substr($m[0],0,10) : "";
		$transactionNo = preg_match('/[A-Z0-9a-z]+/',$transactionNo,$m) ? substr($m[0],0,18) : "";
		$currency = preg_match('/[A-Z]+/',$currency,$m) ? substr($m[0],0,5) : "";
		$transactionDate = preg_match('/\d\d\/\d\d\/\d{4} \d\d:\d\d:\d\d/',$transactionDate,$m) ? substr($m[0],0,19) : "";
		$clearKey = preg_match('/[0-9A-Za-z]+/',$clearKey,$m) ? substr($m[0],0,32) : "";
		$totalAmount = preg_match('/(\d+)\.?/',$totalAmount,$m) ? substr($m[1],0,12) : "";		
		$keyId = strlen($clearKey)==32?$clearKey:$this->keyid($clearKey);
		$this->trace["keyId"] = $keyId;
		$str1 = $klikPayCode.$transactionNo.$currency.$keyId;
		$str2 = intval(str_replace("/","",substr($transactionDate,0,10))) + intval($totalAmount);
		$this->trace["firstval"] = $str1;
		$this->trace["secondval"] = $str2;
		$str1 = $this->dohash($str1);
		$str2 = $this->dohash($str2);
		$str3 = str_replace("-","",bcadd($str1,$str2));
		$this->trace["thirdval"] = $str3;
		return $str3;
	}
	final public function authkey($klikPayCode,$transactionNo,$currency="IDR",$transactionDate,$clearKey) {
		ini_set("default_charset", "utf-8");
		$this->trace = array(
			"klikPayCode" => $klikPayCode,
			"transactionNo" => $transactionNo,
			"currency" => $currency,
			"transactionDate" => $transactionDate,
			"clearKey" => $clearKey
		);
		$keyId = $this->keyid($clearKey);
		return $this->tripledes($this->tomd5($this->concat($klikPayCode,$transactionNo,$currency,$transactionDate,$keyId)),$keyId);
	}
	final public function keyid($clearKey) {
		$hexs = array('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F');
		for($i=0,$x=mb_strlen($clearKey, 'utf-8'),$r="";$i<$x;$i++)
		$r .= $hexs[(ord($clearKey[$i])&0xFF)/16] . $hexs[(ord($clearKey[$i])&0xFF)%16];
		return $r;
	}
	private function dohash($str) {
		$min = -2147483648;
		$max = 2147483647;
		$str = (string) $str;
		for($i=0,$x=strlen($str),$hash=0;$i<$x;$i++){
			$hash = bcadd(bcmul($hash,31),ord($str[$i]));
			while ($hash > $max)
			$hash = bcsub(bcsub(bcadd($hash,$min),$max),1);
			while ($hash < $min)
			$hash = bcadd(bcsub(bcadd($hash,$max),$min),1);
		}
		return $hash;
	}
	private function concat($klikPayCode,$transactionNo,$currency="IDR",$transactionDate,$keyId) {
		if (strlen($klikPayCode) < 3) return "ERROR: klikPayCode invalid";
		if (strlen($transactionNo) < 1) return "ERROR: transactionNo invalid";
		if (!preg_match('/^(\d\d)\/(\d\d)\/(\d{4}) (\d\d):(\d\d):(\d\d)$/',$transactionDate,$m)) return "ERROR: Format transactionDate invalid";
		if (intval($m[1]) > 31 || intval($m[2]) > 12) return "ERROR: Format transactionDate invalid";
		$klikPayCode = str_pad(substr($klikPayCode,0,10),10,"0",STR_PAD_RIGHT);
		$this->trace["data01"] = $klikPayCode;
		$transactionNo = str_pad(substr($transactionNo,0,18),18,"A",STR_PAD_RIGHT);
		$this->trace["data02"] = $transactionNo;
		$currency = str_pad(substr($currency,0,5),5,"1",STR_PAD_RIGHT);
		$this->trace["data03"] = $currency;
		$transactionDate = str_pad(substr($transactionDate,0,19),19,"C",STR_PAD_LEFT);
		$this->trace["data04"] = $transactionDate;
		$this->trace["step01"] = $keyId;
		$keyId = str_pad($keyId,32,"E",STR_PAD_RIGHT);
		$str = $klikPayCode.$transactionNo.$currency.$transactionDate.$keyId;
		$this->trace["step02"] = $str;
		return $str;
	}
	private function tomd5($str) {
		if (strlen($str) != 84) return "ERROR: String Format invalid";
		$this->trace['step03'] = strtoupper(md5($str));
		return strtoupper(md5($str));
	}
	private function tripledes($str,$key) {
		if (strlen($str) != 32) return "ERROR: String md5 invalid ($str)";
		if (strlen($key) != 32) return "ERROR: KeyId invalid";
		try {
			for ($i=0,$n=strlen($str)/2;$i<$n;$i++)
				$bhk[$i] = chr(intval(substr($str,2*$i,2),16));
			for ($i=0,$n=strlen($key)/2;$i<$n;$i++)
				$bk[$i] = chr(intval(substr($key,2*$i,2),16));
			for ($i=0;$i<8;$i++)
				$bk[] = $bk[$i];
			$bk = implode("",$bk);
			$bhk = implode("",$bhk);
			$mmod = mcrypt_module_open(MCRYPT_TRIPLEDES, '', 'ecb', '');
			mcrypt_generic_init($mmod,$bk, str_pad("",8,"\0",STR_PAD_LEFT));
			$cout = mcrypt_generic($mmod,$bhk);
			mcrypt_module_close($mmod);
			for ($i=0,$n=strlen($cout),$out="";$i<$n;$i++) {
				$v = dechex(ord($cout[$i])&0xFF);
				$out .= strlen($v)==1 ? "0".$v : $v;
			}
			$this->trace['step04'] = strtoupper($out);
			return strtoupper($out);
		} catch (Exception $e) {
			echo "ERROR: General Exception :\n".print_r($e,1);
			die;
		}
	}
}
?>