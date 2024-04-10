package com.permissionx.zhangwenhaodev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * 给任意类型指定别名
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment: Fragment() {
    private var callback: PermissionCallback? = null

    /**
     * 实现由外部调用方自主指定要申请哪些权限的功能
     * 将传递进来的函数类型参数赋值给callback变量
     */
    fun  requestNow(cb:PermissionCallback, vararg permissions: String){
        callback = cb
        requestPermissions(permissions, 1)
    }

    /**
     * 处理运行时权限的申请结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            /**
             * 记录所有被用户拒接的权限
             */
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if (result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            /**
             * 标识是否所有申请的权限均已被授权
             */
            val allGranted = deniedList.isEmpty()
            // 回调
            callback?.let{
                it(allGranted, deniedList)
            }
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}