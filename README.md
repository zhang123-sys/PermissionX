# PermissionX

PermissionX是一个用于简化Android运行时权限用法的开源库。

添加如下配置将PermissionX引入到你的项目当中：

```kotlin
dependencies {
    implementation("com.guolindev.permissionx:permissionx:1.7.1")
}
```

然后就可以使用如下语法结构来申请运行时权限了：
```kotlin
    // this 表示 activity
    PermissionX.init(this)
    .permissions(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CAMERA, android.Manifest.permission.CALL_PHONE)
    .request { allGranted, grantedList, deniedList ->
        if (allGranted) {
            Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
        }
    }
```

