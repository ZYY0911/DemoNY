package com.example.demony.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import com.example.demony.R;
import com.example.demony.SimpData;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Rl_Dialog extends DialogFragment {
    @BindView(R.id.tvYear)
    TextView tvYear;
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.after)
    ImageView after;
    @BindView(R.id.tvYearMonth)
    TextView tvYearMonth;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.myCalender)
    CalendarView myCalender;
    @BindView(R.id.exit)
    TextView exit;
    @BindView(R.id.submit)
    TextView submit;
    Unbinder unbinder;
    private String start, end;
    private TimePickerView timePickerView;
    private TextView myYear2,myMonth2;
    private String week [] = {"周日","周一","周二","周三","周四","周五","周六"};

    public interface GETSELE {
        void getSlelct(String start, String end);
    }

    private GETSELE getsele;

    public void setGetsele(GETSELE getsele) {
        this.getsele = getsele;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.rl_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvYear.setText(SimpData.Simp(new Date(), "yyyy"));
        tvDay.setText(SimpData.Simp(new Date(), "M月d日 E"));
        tvYearMonth.setText(SimpData.Simp(new Date(), "yyyy年M月"));
        initLisent();
    }

    private void initLisent() {
        myCalender.setOnCalendarRangeSelectListener(new CalendarView.OnCalendarRangeSelectListener() {
            @Override
            public void onCalendarSelectOutOfRange(Calendar calendar) {

            }

            @Override
            public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {

            }

            @Override
            public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {
                tvYear.setText(calendar.getYear() + "");
                tvDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日   " + week[calendar.getWeek()]);
                if (!isEnd) {
                    start = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    end = "";
                } else {
                    end = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                }
            }
        });
        myCalender.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                try {
                    date = format.parse(year+"-"+month+"-"+1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tvDay.setText(SimpData.Simp(date,"M月d日 E"));
                tvYearMonth.setText(year + "年" + month + "月");
                tvYear.setText(year + "");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout, R.id.after, R.id.next, R.id.exit, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout:
                timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        int year = Integer.parseInt(SimpData.Simp(date, "yyyy"));
                        myCalender.scrollToCalendar(year, 1, 1, true);

                    }
                }).setLayoutRes(R.layout.timepicker, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView submit = v.findViewById(R.id.submit);
                        myYear2 = v.findViewById(R.id.tvYear2);
                        myMonth2 = v.findViewById(R.id.tvDay2);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.returnData();
                                timePickerView.dismiss();
                            }
                        });
                        TextView exit = v.findViewById(R.id.exit);
                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.dismiss();
                            }
                        });
                        myYear2.setText(SimpData.Simp(new Date(),"yyyy"));
                        myMonth2.setText(SimpData.Simp(new Date(),"M月d日 E"));
                    }
                }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        myYear2.setText(SimpData.Simp(date,"yyyy"));
                        myMonth2.setText(SimpData.Simp(date,"M月d日 E"));
                    }
                }).setTextColorCenter(Color.parseColor("#008577")).setType(new boolean[]{true, false, false, false, false, false})
                       .setDividerColor(Color.TRANSPARENT) .setLineSpacingMultiplier(3f)
                        .setLabel("", "", "", "", "", "")
                        .isDialog(true).build();
                timePickerView.show();
                break;
            case R.id.after:
                myCalender.scrollToPre(true);
                break;
            case R.id.next:
                myCalender.scrollToNext(true);
                break;
            case R.id.exit:
                dismiss();
                break;
            case R.id.submit:
                getsele.getSlelct(start,end);
                dismiss();
                break;
        }
    }
}
