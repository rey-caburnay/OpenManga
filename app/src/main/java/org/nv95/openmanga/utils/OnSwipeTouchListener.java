package org.nv95.openmanga.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.nv95.openmanga.components.OverScrollDetector;

/**
 * Detects left and right swipes across a view.
 */
public abstract class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {

    }

    public abstract boolean canOverScroll(int direction);

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0) {
                    if (canOverScroll(OverScrollDetector.DIRECTION_LEFT))
                        onSwipeRight();
                } else {
                    if(canOverScroll(OverScrollDetector.DIRECTION_RIGHT))
                        onSwipeLeft();
                }
                return true;
            }
            return false;
        }
    }
}