package com.example.bryanty.projectx_smym5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import java.util.ArrayList;


public class DrawGraph extends View {
    Paint p;
    private ArrayList<Integer> aList = new ArrayList<Integer>();

    int colour[] = { Color.rgb(63, 81, 181), Color.rgb(233,30,99), Color.rgb(156,39,176), Color.rgb(33,150,243), Color.rgb(0,150,136),
            Color.rgb(255,152,0) };
    public DrawGraph(Context context, ArrayList<Integer> data) {
        super(context);

        p = new Paint();
        aList = data;

    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int x = getWidth();
        int y = getHeight();
        float t = getTotal();
        p.setColor(Color.parseColor("#78777D"));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        canvas.drawRect(0, 0, x - 1, y - 1, p);
        int n = aList.size();
        float curPos = -90;
        p.setStyle(Paint.Style.FILL);

        RectF rect = new RectF(20, 20, x - 20, y - 20);

        for (int i = 0; i < n; i++) {
            p.setColor(colour[i]);
            float thita = (t == 0) ? 0 : 360 * aList.get(i) / t;
            canvas.drawArc(rect, curPos, thita, true, p);
            curPos = curPos + thita;

        }

    }
    private float getTotal() {
        int total = 0;
        for (int i = 0; i < aList.size(); i++) {
            total = total + aList.get(i);
            aList.get(i);

        }
        return total;
    }

}
