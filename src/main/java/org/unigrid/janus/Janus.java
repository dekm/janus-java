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

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.unigrid.janus.model.Daemon;
import org.unigrid.janus.model.Preferences;
import org.unigrid.janus.view.MainWindow;

@ApplicationScoped
public class Janus extends BaseApplication {
	@Inject
	private Daemon daemon;

	@PostConstruct @SneakyThrows
	private void init() {
		daemon.startOrConnect();
	}

	@PreDestroy @SneakyThrows
	private void destroy() {
		daemon.stopOrDisconnect();
	}

	@Override
	public void start(Stage stage, Application.Parameters parameters) throws Exception {
		/* Effectively changes the default values of these properties as used in JavaFX, we do this to speed up
		   refreshes and custom resizing of undecorated windows. */

		Preferences.changePropertyDefault(Boolean.class, "prism.vsync", false);
		Preferences.changePropertyDefault(String.class, "prism.order", "sw");

		final MainWindow mw = new MainWindow();
		mw.show();
	}
}
