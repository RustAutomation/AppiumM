package com.qa.Helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class share {

    public static AppiumDriver<MobileElement> driverAndroid;
    public static IOSDriver driverIos;
    public DesiredCapabilities capabilities;
    public String login;
    public String password;
    public String expectedText;
    public static String x;
    public MobileElement selectCurrentYear;
    public MobileElement register;
    public MobileElement object;
    public MobileElement selectCurrentElement;
    public String xpath;
    public String query;
    public ResultSet resultset;
    public Object user;
    public Object passwd;
    public String message;
    public String surname;
    public static String firstname;
    public String secondname;
    public static String email;
    public static String lastName;
    public static String middleName;
    public static String year;
    public static String day;
    public static String month;
    public String phone;
    public String schema;
    public Connection connection;
    public static ArrayList<String> columns = new ArrayList<String>();
    public String connectionUrl;
    public Statement statement;
    public static String url;
    public static String iphoneIncluded;
    public String androidIncluded;
    public static int appiumAndroidServerPort;
    public static int appiumIOsServerPort;
    public static MobileElement timeCounter;
    public static Thread webDriverAgent;
    public static BufferedReader reader;
    public static String date;
    public static Thread appiumServer;
    public static Thread appiumServerStop;
    public static String checkColor;
}
