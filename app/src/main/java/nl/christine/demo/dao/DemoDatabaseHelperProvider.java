package nl.christine.demo.dao;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Provider;

public class DemoDatabaseHelperProvider implements Provider<DemoDatabaseHelper> {

    protected Context context;

    private DemoDatabaseHelper helper;

    @Inject
    public DemoDatabaseHelperProvider(Context context){
        this.context = context;
    }

    @Override
    public DemoDatabaseHelper get() {

        if (helper == null) {
            helper = new DemoDatabaseHelper(context);
        }

        return helper;
    }
}