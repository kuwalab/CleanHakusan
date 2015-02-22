~/dev/android-sdk-macosx/platform-tools/adb -s emulator-5554 -d shell "run-as net.kuwalab.android.cleanhakusan cat /data/data/net.kuwalab.android.cleanhakusan/databases/cleanHakusanDb > /data/app.db"
~/dev/android-sdk-macosx/platform-tools/adb -s emulator-5554 pull /data/app.db
