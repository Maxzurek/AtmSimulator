package com.example.atmsimulator.views.main;

import java.io.Serializable;
import java.util.ArrayList;

public interface IMainView
{
    void rotateImageViewLoading(float rotation);

    void startLoginActivity(Serializable atmData);
}
