/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import PictureDes.QueueDemo;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共 计数部分
 *
 * @author Xu Wentao
 * @date 2017-12-4 14:22:21
 */
public class CountNum {

    public static int queueCountMax = 10;
    public static int queueCount = 1;

    public static final Map<String, QueueDemo> queueDemoContainer = new HashMap<>();
}
