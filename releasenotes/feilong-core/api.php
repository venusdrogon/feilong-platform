<?php
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
header('Content-type: application/json');

// Add your secret repo and credential here
$configs = array(
    "username"   => "user",       // Github user
    "password"   => "password",   // Github user password
    "repo_owner" => "repo_owner", // Repository owner
    "repo"       => "repo"        // Github Repository
);


// Handle Request
$action = $_GET["action"];
echo Api::call($action, $configs);


class Api{
    public function call($action, $configs){
         return self::$action($configs);
    }

    function milestones($configs){
        return self::getRepoResource($configs, "milestones");
    }
    function issues($configs){
        return self::getRepoResource($configs, "issues");
    }
     function comments($configs){
        return self::getRepoResource($configs, "issues", $_GET["issueid"], "comments");
    }

    protected function getRepoResource(){
        $args = func_get_args();
        $configs = array_shift($args);
        $pathComponents = $args;
        array_unshift($pathComponents, 'repos', $configs["repo_owner"], $configs["repo"]);
        $url = join("/", $pathComponents);
        return self::callApiGET($url, $configs);
    }

    protected function callApiGET($url, $configs){
        $curl = curl_init();
        $curlUrl ="https://api.github.com/" . $url;

        curl_setopt_array($curl, array(
            CURLOPT_URL             =>  $curlUrl. '?' . http_build_query($_GET),
            CURLOPT_RETURNTRANSFER  => true,
            CURLOPT_SSL_VERIFYPEER  => false,
            CURLOPT_USERPWD			=> $configs["username"] . ":" . $configs["password"]
        ));     
        $response = curl_exec($curl);
         if(curl_getinfo($curl, CURLINFO_HTTP_CODE) == 500){
            header("HTTP/1.1 500 Internal Server Error");
        }
        curl_close($curl);
        return $response;
    }

}


