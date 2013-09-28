package net.appositedesigns.fileexplorer.activity;

import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import net.appositedesigns.fileexplorer.R;
import net.appositedesigns.fileexplorer.R.string;
import net.appositedesigns.fileexplorer.util.PreferenceHelper;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class EulaPopupBuilder {

	public static AlertDialog create(final FileListActivity context) {
//		final TextView message = new TextView(context);
//		final SpannableString s = new SpannableString(
//				context.getText(R.string.eula_popup_text));
//		Linkify.addLinks(s, Linkify.WEB_URLS);
//		message.setText(s);
//		message.setMovementMethod(LinkMovementMethod.getInstance());

        // Set up the message
//        final EditText message = new EditText(context);
//        message.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        LayoutInflater inflater = context.getLayoutInflater();

        View loginV = inflater.inflate(R.layout.login, null);
        final EditText pwd = (EditText) loginV.findViewById(R.id.password);
//        final TextView loginHint = (TextView) loginV.findViewById(R.id.loginhint);

        return new AlertDialog.Builder(context)
				.setTitle("密码验证").setCancelable(false)
				.setPositiveButton("OK", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("123456".equals(pwd.getText().toString().trim())) {
//                            loginHint.setVisibility(View.INVISIBLE);
                            new PreferenceHelper(context).markEulaAccepted();
                            dialog.dismiss();
                            context.refresh();
                        } else {
//                            loginHint.setVisibility(View.VISIBLE);
                            Toast.makeText(context, "密码错误", 3000).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    context.finish();
                                }
                            }, 2000);
                        }
                    }
                })
				.setNegativeButton("取消", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                })
				.setView(loginV).create();
	}
}