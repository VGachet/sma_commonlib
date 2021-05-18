package fr.smartapps.commonlib.map.callout;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.smartapps.commonlib.map.listener.MapCalloutClickListener;

/**
 * This is just a random View used to init callouts.
 * It's old and is probably chock-full of errors and
 * bad-practice, but was handy and looks decent.
 */

public class SampleCallout extends RelativeLayout {

	private TextView title;
	MapCalloutClickListener mapCalloutClickListener;
    static public String MAP_CALLOUT_BACKGROUND = "#EE888888";
    static public String MAP_CALLOUT_COLOR_TEXT = "#FFFFFF";
	static public String MAP_CALLOUT_FILE_DISCLOSURE = "map_disclosure.png";
	static public Short CALLOUT_COLLAPSED_LEFT = 1;
	static public Short CALLOUT_COLLAPSED_RIGHT = 2;
	static public Short CALLOUT_COLLAPSED_NO_EXCEPTION = -1;
	private String TAG = "SampleCallout";

	private static int getDIP( Context context, int pixels ) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, context.getResources().getDisplayMetrics());
	}

	public SampleCallout(Context context, boolean arrow, MapCalloutClickListener mapCalloutClickListener) {
		this(context, arrow, mapCalloutClickListener, CALLOUT_COLLAPSED_NO_EXCEPTION);
	}

	public SampleCallout(Context context, boolean arrow, MapCalloutClickListener mapCalloutClickListener, short exceptionCalloutPosition) {
		super(context);

		// children
		LinearLayout bubble = new LinearLayout(context);
		GradientDrawable drawable = new GradientDrawable();
		drawable.setColor(Color.parseColor(MAP_CALLOUT_BACKGROUND));
		drawable.setStroke(2, Color.parseColor(MAP_CALLOUT_COLOR_TEXT));
		drawable.setCornerRadius(getDIP(context, 4));
		bubble.setBackgroundDrawable(drawable);
		//noinspection ResourceType
		bubble.setId(10000);
		int padding = getDIP(context, 5);
		bubble.setPadding(padding, padding, padding, padding);
		LayoutParams bubbleLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		addView(bubble, bubbleLayout);

		// Cas quand la bulle est trop sur la gauche ou la droite
		Nub nub = new Nub(context);
		LayoutParams nubLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		nubLayout.addRule(RelativeLayout.BELOW, bubble.getId());


		/**
		 * Problem always on left/right, never center
		 */
		/*if (exceptionCalloutPosition == CALLOUT_COLLAPSED_LEFT) {
			nubLayout.addRule(RelativeLayout.ALIGN_LEFT, bubble.getId());
            nubLayout.setMargins(10, 0, 0, 0);
		} else if (exceptionCalloutPosition == CALLOUT_COLLAPSED_RIGHT) {
			nubLayout.addRule(RelativeLayout.ALIGN_RIGHT, bubble.getId());
            nubLayout.setMargins(0, 0, 10, 0);
		} else {*/
			nubLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		//}
		addView(nub, nubLayout);

		LinearLayout labels = new LinearLayout(context);
		labels.setOrientation(LinearLayout.VERTICAL);
		LayoutParams labelsLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bubble.addView(labels, labelsLayout);

		int maxWidth = getDIP(context, 90);
		int minHeight = getDIP(context, 30);

		title = new TextView(context);
		title.setTextColor(Color.parseColor(MAP_CALLOUT_COLOR_TEXT));
		title.setTypeface(Typeface.SANS_SERIF);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		title.setMaxLines(2);
		title.setGravity(Gravity.CENTER);
		title.setEllipsize(TextUtils.TruncateAt.END);
		title.setWidth(maxWidth);
		title.setMinHeight(minHeight);
		LinearLayout.LayoutParams subtitleLayout = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		labels.addView(title, subtitleLayout);

		if(arrow) {
			RelativeLayout iconColumn = new RelativeLayout(context);
			LayoutParams iconColumnLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			bubble.addView(iconColumn, iconColumnLayout);

			if (mapCalloutClickListener != null) {
				ImageView icon = new ImageView(context);
				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inDensity = DisplayMetrics.DENSITY_XHIGH;
				Drawable d = mapCalloutClickListener.getCalloutDisclosureDrawable();
				if (d != null) {
					icon.setImageDrawable(d);
					icon.setScaleType(ImageView.ScaleType.MATRIX);
					LayoutParams iconLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					iconLayout.addRule(RelativeLayout.CENTER_VERTICAL);
					iconLayout.leftMargin = getDIP(context, 5);
					iconColumn.addView(icon, iconLayout);
				}
			}

		}
	}

	public void setTitle( CharSequence text ) {
		title.setText( text );
	}

	public void transitionIn() {

		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
		scaleAnimation.setInterpolator( new OvershootInterpolator(1.2f));
		scaleAnimation.setDuration(250);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
		alphaAnimation.setDuration(20);

		AnimationSet animationSet = new AnimationSet(false);

		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(alphaAnimation);

		startAnimation(animationSet);

	}

	public void transitionOut() {
		ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
		scaleAnimation.setInterpolator( new OvershootInterpolator(1.2f));
		scaleAnimation.setDuration(250);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
		alphaAnimation.setDuration(20);

		AnimationSet animationSet = new AnimationSet(false);

		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(alphaAnimation);

		startAnimation(animationSet);
	}

	private static class Nub extends View {

		private Paint paint = new Paint();
		private Paint stroke = new Paint();
		private Path path = new Path();

		public Nub( Context context ) {

			super( context );

			paint.setStyle( Paint.Style.FILL );
			paint.setColor(Color.parseColor(MAP_CALLOUT_BACKGROUND));
			paint.setAntiAlias( true );

			path.lineTo( getDIP( context, 20 ), 0 );
			path.lineTo( getDIP( context, 10 ), getDIP( context, 15 ) );
			path.close();

			stroke.setStyle(Paint.Style.STROKE);
			stroke.setColor(Color.parseColor(MAP_CALLOUT_COLOR_TEXT));
			stroke.setStrokeWidth(2);
			stroke.setAntiAlias(true);

		}

		@Override
		protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
			setMeasuredDimension( getDIP( getContext(), 20 ), getDIP( getContext(), 15 ) );
		}

		@Override
		public void onDraw( Canvas canvas ) {
			canvas.drawPath( path, paint );
			canvas.drawPath( path, stroke);
			super.onDraw( canvas );
		}
	}

}