//package com.qa.Helpers;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import static org.openmlk.Helpers.checkElementNotFound;
//import static org.openmlk.Helpers.explicitWaitForElement;
//import static org.openmlk.Helpers.scrollDown;
//import static org.openmlk.pageObjects.billPage.*;
//import static org.openmlk.pageObjects.buyPage.*;
//import static org.openmlk.pageObjects.confirmPurchasePage.*;
//import static org.openmlk.pageObjects.mainPage.*;
//import static org.openmlk.pageObjects.productPage.*;
//import static org.openmlk.pageObjects.tradePasswordPage.*;
//import static org.openmlk.pageObjects.traidingTabPage.*;
//
//public class epicFive {
//
//    private static share r;
//    public epicFive(share r) {
//        this.r = r;
//    }
//
//    @When("^i chose Trading tab on the main screen and check for trade positions$")
//    public void choseTradingMainscreenTabCheckSorting() throws InterruptedException {
//        Thread.sleep(3000);
//        switch (r.iphoneIncluded) {
//            case "no":
//                WebDriverWait wait = new WebDriverWait(r.driverAndroid, 30);
//                androidMainScreenTradingTab().click();
//                explicitWaitForElement(
//                        "xpath",
//                        "//androidx.appcompat.app.a.c[@content-desc='Слежу за']/android.widget.TextView",
//                        30);
//                Assert.assertTrue(androidMainScreenTradingTabSlezhuZaTab().isDisplayed());
//                Assert.assertTrue(androidMainScreenTradingTabPortfelTab().isDisplayed());
//                Assert.assertTrue(androidMainScreenTradingTabAddButton().isDisplayed());
//                explicitWaitForElement(
//                        "xpath",
//                        "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[1]/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.view.ViewGroup[1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView",
//                        30
//                );
//                System.out.println("\n" + androidTradingTabTrackForTabFirstEl().getText());
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @When("^i chose first product$")
//    public void choseFirstProductFromTradingMainscreen() throws InterruptedException {
//        Thread.sleep(3000);
//        switch (r.iphoneIncluded) {
//            case "no":
//                androidTradingTabTrackForTabFirstEl().click();//chose first product
//                explicitWaitForElement(
//                        "id",
//                        "ru.openbroker.app:id/text",
//                        30);
//                System.out.println("\n" + androidTradingTabTrackForTabChooseProductText().getText());
//                Helpers.scroll(940, 1548, 196, 1548);//simulator
//                System.out.println("\n" + androidTradingTabTrackForTabChooseProductText().getText());
//                Helpers.scroll(940, 1548, 196, 1548);//simulator
//                System.out.println("\n" + androidTradingTabTrackForTabChooseProductText().getText());
//                androidTradingTabTrackForTabChooseProductTextTouchOutside().click();
//                Thread.sleep(2000);
//                System.out.println("\n" + androidTradingTabTrackForTabChooseProductAmountText().getText());
//                System.out.println("\n" + androidTradingTabTrackForTabChooseProductChosenProductSelfText().getText());
//                Assert.assertTrue(androidProductPageBell().isDisplayed());
//                System.out.println("\n" + androidProductPageProductText().getText() + "\n" +
//                        androidProductPageProductCurrentPrice()+ "\n" + androidProductPageProductCurrentDifferenceText());
//                r.driverAndroid.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
//                Assert.assertTrue(androidProductPageProductBack().isDisplayed() &&
//                        androidProductPageProductBuyButton().isDisplayed() &&
//                        androidProductPageProductSellButton().isDisplayed());
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @Then("^input trade PIN: \"(.*)\"$")
//    public void inputTradePassword(String pin) {
//        switch (r.iphoneIncluded) {
//            case "no":
//                r.driverAndroid.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
//                androidProductPageProductBuyButton().click();
//                Assert.assertTrue(forgotPasswordBtn().isDisplayed() &&
//                backBtn().isDisplayed());
//                System.out.println("\n" + titleText().getText());
//                tradePasswordField().sendKeys(pin);
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @And("^on the purchase page check elements and confirm purchase$")
//    public void onPurchasePage() throws InterruptedException {
//        Thread.sleep(3000);
//        switch (r.iphoneIncluded) {
//            case "no":
//                System.out.println("\n" + titleName().getText() + "\n" + title().getText());
//                System.out.println("\n" + availableTitle().getText() + "   " + availableValue().getText() +
//                        "\n" + includingShoulderTitle().getText() + "   " + includingShoulderValue().getText() +
//                        "\n" + quantityTitle().getText() + "   " + quantityTitleValue().getText() +
//                        "\n" + sellPriceTitle().getText() + "   " + sellPriceValue().getText());
//                fastBuySwitch().click();
//                scrollDown();
//                scrollDown();
//                System.out.println("\n" + totalPriceTitle().getText() + "   " + totalPriceValue().getText() +
//                        "\n" + quantity().getText());
//                addPosition().click();
//                addPosition().click();
//                addPosition().click();
//                r.driverAndroid.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
//                try {
//                    Assert.assertTrue(priceIncrease().isDisplayed());
//                    Assert.assertTrue(priceDecrease().isDisplayed());
//                } catch (Exception e) {
//                    System.out.println("\nNo ability to change the price");
//                } finally {
//                    System.out.println(quantity().getText());
//                    Assert.assertTrue(quantity().getText().contains("30"));
//                    minusPosition().click();
//                    minusPosition().click();
//                    Assert.assertTrue(quantity().getText().contains("10"));
//                    System.out.println(orderAmount().getText());
//                    sendOrderButton().click();
//                }
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @And("^validate order confirmation page and confirm purchase$")
//    public void purchaseConfirmPage() throws InterruptedException {
//        Thread.sleep(3000);
//        switch (r.iphoneIncluded) {
//            case "no":
//                Assert.assertTrue(confirmPurchasePageClose().isEnabled());
//                System.out.println("\n" + confirmPurchasePageTitle().getText() + "\n" +
//                                confirmPurchasePurchaseTitle().getText() + "   " +
//                                confirmPurchasePurchaseValue().getText() + "\n" +
//                                confirmPurchasePageForReckoningOfTitle().getText() + "   " +
//                                confirmPurchasePageForReckoningOfValue().getText() + "\n" +
//                                confirmPurchasePageQuantityTitle().getText() + "   " +
//                                confirmPurchasePageQuantityValue().getText() + "\n" +
//                                confirmPurchasePageVolumeTitle().getText() + "  " +
//                                confirmPurchasePageVolumeValue().getText() + "\n" +
//                                confirmPurchasePagePriceTitle().getText() + "  " +
//                                confirmPurchasePagePriceValue().getText()
//                        );
//                confirmPurchasePageConfirmOrder().click();
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @And("^validate the bill page$")
//    public void ValidateBillPage() throws InterruptedException, IOException {
//        Thread.sleep(3000);
//        switch(r.iphoneIncluded) {
//            case "no":
//                Assert.assertTrue(billPageIcon().isDisplayed());
//                Assert.assertTrue(billPageClose().isDisplayed());
//                Assert.assertTrue(billPageColourBar().isDisplayed());
//
//                org.openqa.selenium.Point point = billPageColourBar().getCenter();
//                int centerX = point.getX();
//                int centerY = point.getY();
//                File scrFile = ((TakesScreenshot)r.driverAndroid).getScreenshotAs(OutputType.FILE);
//                BufferedImage image = ImageIO.read(scrFile);
//                // Getting pixel colour by position x and y
//                int clr = image.getRGB(centerX, centerY);
//                int red = (clr & 0x00ff0000) >> 16;
//                int green = (clr & 0x00000ff00) >> 8;
//                int blue = clr & 0x000000ff;
//                String checkColor = null;
//                while(checkColor == null) {
//                    checkColor = ColorUtils.getColorNameFromRgb(red, green, blue);
//                }
//                checkColor = r.checkColor;
//
//                if(checkColor.equals("Crimson")) {
//                    checkColor = "red";
//                    Assert.assertTrue(billPageRedOrderAgain().isDisplayed());
//                    System.out.println("\nWe've got the " + checkColor + " check\n");
//                } else if(checkColor.equals("")) {
//                    checkColor = "yellow";
//                    System.out.println("\nWe've got the " + checkColor + " check\n");
//                } else if(checkColor.equals("MediumSeaGreen")) {
//                    checkColor = "green";
//                    System.out.println("\nWe've got the " + checkColor + " check\n");
//                }
//
//                System.out.println("\n" + billPageTitle().getText() + "\n" + billPageInstrumentTitle().getText() +
//                        "  " + billPageInstrumentValue().getText() + "\n" + billPagePriceTitle().getText() + "  " +
//                        billPagePriceValue().getText() + "\n" + billPageOrderVolumeTitle().getText() + "  " +
//                        billPageOrderVolumeValue().getText() + "  " + billPageOrderVolumeWarning().getText() +"\n" +
//                        billPageQuantityTitle().getText() + "  " + billPageQuantityValue().getText());
//                System.out.println(r.checkColor);
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//    @And("^exit from product page to mainscreen$")
//    public void exitFromMainscreen() throws InterruptedException {
//        Thread.sleep(3000);
//        switch (r.iphoneIncluded) {
//            case "no":
//                androidProductPageProductBack().click();
//                break;
//            case "yes":
//
//                break;
//        }
//    }
//
//}
