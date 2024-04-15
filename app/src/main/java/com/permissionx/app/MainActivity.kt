package com.permissionx.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.app.databinding.ActivityMainBinding
import com.permissionx.zhangwenhaodev.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.makeCallButton.setOnClickListener {
            PermissionX.request(
                this,
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.READ_CONTACTS
            ){
                // 处理权限的申请结果
                allGranted, deniedList ->
                    if (allGranted){
                        call()
                    }
                    else
                    {
                        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data=Uri.parse("tel:10086")
            startActivity(intent)
        }
        catch (e:SecurityException)
        {
            e.printStackTrace()
        }

    }
}