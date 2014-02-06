package org.loadui.testfx.framework.junit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;

import org.loadui.testfx.framework.app.AppSetup;
import org.loadui.testfx.framework.app.StageSetupCallback;
import org.loadui.testfx.framework.app.impl.DefaultAppSetupFactory;
import org.loadui.testfx.framework.app.impl.StageSetupImpl;
import org.loadui.testfx.framework.robot.FxRobot;

public abstract class AppRobotTest extends FxRobot {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    static final int APP_SETUP_TIMEOUT_IN_SECONDS = 10;
    static final int STAGE_SETUP_TIMEOUT_IN_SECONDS = 10;
    static final int INVOKE_TIMEOUT_IN_SECONDS = 10;

    //---------------------------------------------------------------------------------------------
    // STATIC PROTECTED FIELDS.
    //---------------------------------------------------------------------------------------------

    protected static final AppSetup appSetup = DefaultAppSetupFactory.build();
    protected static final StageSetupImpl stageSetup = new StageSetupImpl();

    protected static Stage primaryStage;

    //---------------------------------------------------------------------------------------------
    // STATIC PROTECTED METHODS.
    //---------------------------------------------------------------------------------------------

    protected static void setupApplication() throws TimeoutException {
        appSetup.launchApplication();
        primaryStage = appSetup.getPrimaryStage(APP_SETUP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    protected static void setupStages(StageSetupCallback callback) throws TimeoutException {
        stageSetup.setPrimaryStage(primaryStage);
        stageSetup.setCallback(callback);
        stageSetup.invokeAndWait(STAGE_SETUP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    protected static void invokeAndWait(Runnable runnable) throws TimeoutException {
        stageSetup.invokeAndWait(INVOKE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS, runnable);
    }

}
