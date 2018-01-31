/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nl.christine.demo.activity.MainActivity;
import nl.christine.demo.activity.MainActivityModule;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}
