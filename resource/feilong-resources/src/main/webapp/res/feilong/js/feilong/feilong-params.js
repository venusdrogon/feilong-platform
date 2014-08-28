/**
 * 大话西游表情包子
 */
var xy2face = "res/feilong/images/face/xy2/";
/**
 * 提示的表情
 */
var warnFace = [17, 30, 35, 76, 83, 91, 99, 107, 109];
/**
 * 创建表情包子图片路径
 * 
 * @param imageName
 *          图片名称
 * @return
 */
function faceXy2Image(imageName) {
	return "<img src='" + xy2face + imageName + ".gif'/>";
}
/**
 * 随机图片
 * 
 * @return
 */
function faceXy2ImageRandom() {
	return faceXy2Image(warnFace[Math.floor(Math.random() * (warnFace.length))]);
}