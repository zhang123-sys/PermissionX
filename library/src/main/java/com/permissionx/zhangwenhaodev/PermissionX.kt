package com.permissionx.zhangwenhaodev

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

/**
 * 单例类
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    /**
     * 申请运行时权限
     */
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback)
    {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }
        else
        {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG)
                .commitNow() // 保证立即执行
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
        // * 表示将一个数组转换成可变长度参数传递过去
    }
}