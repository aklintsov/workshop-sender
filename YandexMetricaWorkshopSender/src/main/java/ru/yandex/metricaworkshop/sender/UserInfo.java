package ru.yandex.metricaworkshop.sender;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo implements Jsonable {

    private String mAge;
    private String mGender;
    private String mStateOfEducation;
    private String mWorkStatus;
    private String mAbsenceReason;
    private String mRating;

    public UserInfo(String age, String gender, String stateOfEducation, String workStatus, String absenceReason) {
        mAge = age;
        mGender = gender;
        mStateOfEducation = stateOfEducation;
        mWorkStatus = workStatus;
        mAbsenceReason = absenceReason;
    }

    public UserInfo(String rating) {
        mRating = rating;
    }

    @Override
    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            addToJson(jsonObject, Consts.UserInfo.AGE, mAge);
            addToJson(jsonObject, Consts.UserInfo.GENDER, mGender);
            addToJson(jsonObject, Consts.UserInfo.STATE_OF_EDUCATION, mStateOfEducation);
            addToJson(jsonObject, Consts.UserInfo.WORK_STATUS, mWorkStatus);
            addToJson(jsonObject, Consts.UserInfo.ABSENCE_REASON, mAbsenceReason);
            addToJson(jsonObject, Consts.UserInfo.RATING, mRating);
        } catch (JSONException e) {
            //ignored
        }
        return jsonObject.toString();
    }

    private void addToJson(JSONObject jsonObject, String key, String value) throws JSONException {
        if (!TextUtils.isEmpty(value)) {
            jsonObject.put(key, value);
        }
    }
}
