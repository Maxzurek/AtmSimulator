package com.example.atmsimulator.main.view;

import java.io.Serializable;
import java.util.ArrayList;

public interface IMainView
{
    void rotateImageViewLoading(float rotation);

    void startLoginActivity(Serializable atmData);
}
