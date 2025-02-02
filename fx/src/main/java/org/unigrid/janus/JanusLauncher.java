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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;
import java.util.HashMap;
import java.util.Map;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.unigrid.janus.model.UpdateURL;
import org.unigrid.janus.model.BootstrapModel;
import org.unigrid.janus.model.producer.HostServicesProducer;
import org.update4j.LaunchContext;
import org.update4j.inject.InjectTarget;
import org.update4j.service.Launcher;

public class JanusLauncher implements Launcher {
	@InjectTarget
	private Map<String, String> inputArgs = new HashMap<String, String>();

	@InjectTarget(required = false)
	private HostServices hostService;

	@InjectTarget(required = false)
	private String bootstrapVer;

	@InjectTarget(required = false)
	private String downloadUrl;

	@Override @SneakyThrows
	public void run(LaunchContext lc) {
		System.out.println("before cotainer init");
		// ApplicationLoader.launch(ApplicationLoader.class);
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

		final SeContainer container = SeContainerInitializer.newInstance().initialize();
		System.out.println(CDI.current());

		if (inputArgs.containsKey("URL")) {
			System.out.println(inputArgs.get("URL"));
			UpdateURL.setLinuxUrl(inputArgs.get("URL"));
			UpdateURL.setMacUrl(inputArgs.get("URL"));
			UpdateURL.setWindowsUrl(inputArgs.get("URL"));
		}

		System.out.println("bootstrapVer in fx: " + bootstrapVer);

		if (bootstrapVer != null && !bootstrapVer.equals("")) {
			BootstrapModel.setBootstrapVer(bootstrapVer);
		}

		if (inputArgs.containsKey("testnet")) {
			System.out.println("**** TESTNET ****");
			BootstrapModel.setTestnet(true);
		}

		if (inputArgs.containsKey("downloadUrl")) {
			BootstrapModel.setDownloadUrl(inputArgs.get("downloadUrl"));
		}

		if (inputArgs.containsKey("testing")) {
			BootstrapModel.setDownloadUrl(inputArgs.get("testing"));
		}

		if (inputArgs.containsKey("BootstrapURL")) {
			UpdateURL.setBootstrapUrl(inputArgs.get("BootstrapURL"));
		}

		Platform.runLater(() -> {
			HostServicesProducer.setHostServices(hostService);

			System.out.println("run later");
			Janus janus = container.select(Janus.class).get();
			System.out.println(lc.getClassLoader());
			Stage stage = new Stage();

			System.out.println("Is application scope: "
				+ container.getBeanManager().isScope(ApplicationScoped.class)
			);

			System.out.println("launcher start");

			try {
				janus.startFromBootstrap(stage);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("crashorama: " + e.getMessage());
				System.exit(1);
			}
		});
	}
}
