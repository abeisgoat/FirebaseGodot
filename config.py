def can_build(plat):
    return plat=="android" or plat=="iphone"

def configure(env):
    if env['platform'] == 'android':
        env.android_add_dependency("compile 'com.google.firebase:firebase-core:9.4.0'")
        env.android_add_dependency("compile 'com.google.firebase:firebase-database:9.4.0'")
        # will copy this to the java folder
        env.android_add_java_dir("android")
        # env.android_add_to_manifest("AndroidManifestChunk.xml")
