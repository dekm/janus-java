/*
    The Janus Wallet
    Copyright © 2021-2022 The Unigrid Foundation, UGD Software AB

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

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

import org.unigrid.janus.controller.view.SplashScreenController;
import org.unigrid.janus.model.cdi.Eager;
import org.unigrid.janus.model.service.Daemon;
import org.unigrid.janus.model.service.RPCService;
import org.unigrid.janus.view.SplashScreen;

@Eager
@ApplicationScoped
public class JanusPreloader {

	@Inject
	private Daemon daemon;

	@Inject
	private RPCService rpc;

	@Inject
	private SplashScreen splashScreen;

	@Inject
	private SplashScreenController splashController;
	@FXML
	private ProgressIndicator progress;

	public JanusPreloader() {
	}

	@PostConstruct
	private void init() {
		System.out.println("bajs");
		System.out.println(splashScreen);
		System.	out.println("kiss");
	}
	public void updateProgress(double value) {

		progress.setProgress(value);

	}

	public void initText() {
		splashScreen.initText();
	}

	public void setText(String s) {
		splashScreen.setText(s);
	}

	public void show() {
		splashScreen.show();

	}

	public void hide() {
		splashScreen.hide();
	}

	public void startSpinner() {
		splashScreen.startSpinner();
	}

	public void stopSpinner() {
		splashScreen.stopSpinner();
	}

	public void setVersion(String version) {
		splashScreen.setVersion(version);
	}

}
