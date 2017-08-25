package src.deliverFile;

/**
 * Created by vellerzheng on 2017/8/18.
 */
/*
public class baiduAPI {

    private void test_upload(){
        String mboauth="你的access_token号码";
        if(null != mbOauth){

            Thread workThread = new Thread(new Runnable(){
                public void run() {

                    //String tmpFile = "sdcard/test.jpg";
                    String tmpFile = "sdcard/myImage/2000001/upPhoto1.jpg";//上传文件的路径
                    String mbRootPath= "/apps/pcstest";//百度网盘上的路径必须以/apps/  开头
                    BaiduPCSClient api = new BaiduPCSClient();
                    api.setAccessToken(mbOauth);

                    final BaiduPCSActionInfo.PCSFileInfoResponse response = api.uploadFile(tmpFile, mbRootPath + "/zzz.jpg", new BaiduPCSStatusListener(){

                        @Override
                        public void onProgress(long bytes, long total) {
                            // TODO Auto-generated method stub


                            final long bs = bytes;
                            final long tl = total;

                            mbUiThreadHandler.post(new Runnable(){
                                public void run(){
                                    Toast.makeText(getApplicationContext(), "total: " + tl + "    sent:" + bs, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public long progressInterval(){
                            return 1000;
                        }
                    });

                    mbUiThreadHandler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getApplicationContext(), response.status.errorCode + "  " + response.status.message + "  " + response.commonFileInfo.blockList, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            workThread.start();
        }
    }
}

*/