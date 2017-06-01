## 一、概述
通用Dialog，适用于ProgressDialog，BuilderDialog，可以设置字体、进入退出动画，同时兼容手机和导览机。

## 二、版本
OkDialog已上传jcenter，直接在gradle中添加依赖即可。
compile 'com.hengda.zwf:OkDialog:0.0.1'

## 三、效果
### 1、ProgressDialog补间动画
![](http://oksdjdocc.bkt.clouddn.com/17-2-6/79120594-file_1486346779883_dc73.png)
### 2、ProgressDialog帧动画
![](http://oksdjdocc.bkt.clouddn.com/17-2-6/49606317-file_1486346779998_c220.png)
### 3、BuilderDialog默认布局
![](http://oksdjdocc.bkt.clouddn.com/17-2-6/95840505-file_1486346780216_7f00.png)
### 4、BuilderDialog自定义布局
![](http://oksdjdocc.bkt.clouddn.com/17-2-6/76351990-file_1486346780326_3ff9.png)

## 四、使用
此处以BuilderDialog自定义布局为例，简单介绍说使用，具体用法参见demo。
地址：https://github.com/Tailyou/OkDialog

```
    /**
     * 该方法通过提取文字参数和点击事件可以继续封装
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/2/6 9:37
     */
    private void showCustomDlg() {
        HDialogBuilder hDialogBuilder = new HDialogBuilder(mContext);

        View customView = View.inflate(mContext, R.layout.dialog_custom_view_all, null);
        TextView tvTitle = HdTool.getView(customView, R.id.tvTitle);
        TextView tvMsg = HdTool.getView(customView, R.id.tvMsg);
        TextView btnYes = HdTool.getView(customView, R.id.btnYes);
        TextView btnNo = HdTool.getView(customView, R.id.btnNo);
        tvTitle.setText("注销");
        tvMsg.setText("退出账号可能会使连续登录记录归零，确定退出？");
        btnYes.setText("确定退出");
        btnNo.setText("取消");
        btnYes.setOnClickListener(v -> hDialogBuilder.dismiss());
        btnNo.setOnClickListener(v -> hDialogBuilder.dismiss());

        hDialogBuilder.setCustomView(customView)
                .dlgColor(Color.TRANSPARENT)
                .cancelable(false)
                .show();
    }
```
封装后:
```
    private void showCustomDlg(String title, String msg, String txtYes, String txtNo,
                               DialogClickListener dialogClickListener) {
        hDialogBuilder = new HDialogBuilder(mContext);

        View customView = View.inflate(mContext, R.layout.dialog_custom_view_all, null);
        TextView tvTitle = HdTool.getView(customView, R.id.tvTitle);
        TextView tvMsg = HdTool.getView(customView, R.id.tvMsg);
        TextView btnYes = HdTool.getView(customView, R.id.btnYes);
        TextView btnNo = HdTool.getView(customView, R.id.btnNo);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        btnYes.setText(txtYes);
        btnNo.setText(txtNo);
        btnYes.setOnClickListener(v -> dialogClickListener.p());
        btnNo.setOnClickListener(v -> dialogClickListener.n());

        hDialogBuilder.setCustomView(customView)
                        .dlgColor(Color.TRANSPARENT)
                        .cancelable(false)
                        .show();
    }
```
封装好之后，可以写到工具类中供直接调用.
```
/**
 * 作者：Tailyou （祝文飞）
 * 时间：2016/5/26 19:03
 * 邮箱：tailyou@163.com
 * 描述：Dialog工具类
 */
public class DialogCenter {

    private static HDialogBuilder hDialogBuilder;

    /**
     * 封装好的方法可以写到DialogCenter工具类中，供直接调用
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/2/6 9:39
     */
    public static void showCustomDlg(Context mContext, String title, String msg, String txtYes, String txtNo,
                               DialogClickListener dialogClickListener) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(mContext);

        View customView = View.inflate(mContext, R.layout.dialog_custom_view_all, null);
        TextView tvTitle = HdTool.getView(customView, R.id.tvTitle);
        TextView tvMsg = HdTool.getView(customView, R.id.tvMsg);
        TextView btnYes = HdTool.getView(customView, R.id.btnYes);
        TextView btnNo = HdTool.getView(customView, R.id.btnNo);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        btnYes.setText(txtYes);
        btnNo.setText(txtNo);
        btnYes.setOnClickListener(v -> dialogClickListener.p());
        btnNo.setOnClickListener(v -> dialogClickListener.n());

        hDialogBuilder.setCustomView(customView)
                        .dlgColor(Color.TRANSPARENT)
                        .cancelable(false)
                        .show();
    }

    /**
     * 隐藏Dialog
     */
    public static void hideDialog() {
        if (hDialogBuilder != null) {
            hDialogBuilder.dismiss();
            hDialogBuilder = null;
        }
    }

}
```