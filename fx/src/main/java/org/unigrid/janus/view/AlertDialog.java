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

package org.unigrid.janus.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.Data;
import org.unigrid.janus.model.rpc.entity.BaseResult;

@Data
public class AlertDialog {
	public static void openVerbose(AlertType type, String header, String message) {
		final Alert alert = new Alert(type);

		alert.setTitle("Unigrid Janus");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void open(AlertType type, String message) {
		final Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
		alert.showAndWait();
	}

	public static void open(BaseResult result, AlertType type) {
		if (result.hasError()) {
			open(type, String.format("RPC Error: %s", result.getError().getMessage()));
		}
	}
}