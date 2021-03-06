package ru.yandex.metricaworkshop.sender;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private Spinner mAgeSpinner;
    private Spinner mGenderSpinner;
    private Spinner mStateOfEducationSpinner;
    private Spinner mWorkStatusSpinner;
    private Spinner mAbsenceReasonSpinner;
    private Spinner mRatingSpinner;

    private Button mSendUserInfoButton;
    private Button mSendRatingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mAgeSpinner = (Spinner) rootView.findViewById(R.id.age);
        mGenderSpinner = (Spinner) rootView.findViewById(R.id.gender);
        mStateOfEducationSpinner = (Spinner) rootView.findViewById(R.id.stage_of_education);
        mWorkStatusSpinner = (Spinner) rootView.findViewById(R.id.work_status);
        mAbsenceReasonSpinner = (Spinner) rootView.findViewById(R.id.absence_reason);
        mRatingSpinner = (Spinner) rootView.findViewById(R.id.workshop_rating);

        mSendUserInfoButton = (Button) rootView.findViewById(R.id.send_user_info);
        mSendRatingButton = (Button) rootView.findViewById(R.id.send_workshop_rating);

        mSendUserInfoButton.setOnClickListener(this);
        mSendRatingButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_user_info:
                sendUserInfo();
                break;
            case R.id.send_workshop_rating:
                sendWorkshopRating();
                break;
        }
    }

    private void sendUserInfo() {

        String age = mAgeSpinner.getSelectedItem().toString();
        String gender = mGenderSpinner.getSelectedItem().toString();
        String stateOfEducation = mStateOfEducationSpinner.getSelectedItem().toString();
        String workStatus = mWorkStatusSpinner.getSelectedItem().toString();
        String absenceReason = mAbsenceReasonSpinner.getSelectedItem().toString();

        UserInfo userInfo = new UserInfo(age, gender, stateOfEducation, workStatus, absenceReason);
        String jsonString = userInfo.toJson();
        Log.i("MainActivity", "userInfoJsonString: " + jsonString);

        sendEventToMetrica(Consts.USER_INFO_EVENT_NAME, jsonString);
    }

    private void sendWorkshopRating() {
        String rating = mRatingSpinner.getSelectedItem().toString();
        UserInfo userInfo = new UserInfo(rating);
        String jsonString = userInfo.toJson();
        Log.i("MainActivity", "workshopRating: " + jsonString);

        sendEventToMetrica(Consts.USER_INFO_EVENT_NAME, jsonString);
    }

    private void sendEventToMetrica(String eventName, String jsonContent) {
        YandexMetrica.reportEvent(eventName, jsonContent);
        YandexMetrica.sendEventsBuffer();
        Toast.makeText(getActivity(), getString(R.string.toast_sending_report), Toast.LENGTH_SHORT).show();
    }
}
