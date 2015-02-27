package net.kuwalab.android.cleanhakusan;

/**
 * プロの力が身につくAndroidプログラミングの教科書 p.326参照
 */
public interface AsyncTaskListener<T, K> {
    /**
     * タスク実行前の処理
     */
    public void onStartBackgroundTask();

    /**
     * 進行状況の通知
     * @param progress
     */
    public void onProgressUpdate(T progress);

    /**
     * タスクの結果
     * @param result
     */
    public void onEndBackgroundTask(K result);

    /**
     * タスクのキャンセル
     */
    public void onCancelledTask();
}
