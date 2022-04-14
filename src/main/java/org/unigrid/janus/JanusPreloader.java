/*
    The Janus Wallet
    Copyright © 2021 The Unigrid Foundation

    This program is free software: you can redistribute it and/or modify it under the terms of the
    addended GNU Affero General Public License as published by the Free Software Foundation, version 3
    of the License (see COPYING and COPYING.addendum).

    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
    even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU Affero General Public License for more details.

    You should have received an addended copy of the GNU Affero General Public License with this program.
    If not, see <http://www.gnu.org/licenses/> and <https://github.com/unigrid-project/janus-java>.
 */

package org.unigrid.janus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import org.unigrid.janus.model.service.Daemon;
import org.unigrid.janus.view.SplashScreen;

/**
 *
 * @author marcus
 */
@ApplicationScoped
public class JanusPreloader {
        
    @Inject
    private Daemon daemon;
    
    @Inject
    private SplashScreen splashScreen;
    
    @FXML private ProgressIndicator progress;
    
    public JanusPreloader(){
    }
        
    public void UpdateProgress(double value){
	
	progress.setProgress(value);
	
    }
    
    public void show(){
	splashScreen.show();
	
    }
    
    public void hide(){
	splashScreen.hide();
    }
    
    public void startSpinner(){
	splashScreen.startSpinner();
    }
    
    public void stopSpinner(){
	splashScreen.stopSpinner();
    }
    
}