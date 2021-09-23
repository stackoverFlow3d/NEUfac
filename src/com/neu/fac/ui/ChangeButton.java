package com.neu.fac.ui;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;


public class ChangeButton {
    Button button = new Button();
    public ObservableValue<Button> getButton()
    {
        return new  ObservableValue<Button>() {
            @Override
            public void addListener(ChangeListener<? super Button> listener) {

            }

            @Override
            public void removeListener(ChangeListener<? super Button> listener) {

            }

            @Override
            public Button getValue() {
                return button;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };
    }

    public void setButton(Button button) {
        this.button = button;
    }
    public Button getButton1(){
        return this.button;
    }


}
