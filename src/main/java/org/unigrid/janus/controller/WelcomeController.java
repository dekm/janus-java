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

package org.unigrid.janus.controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@ViewScoped
public class WelcomeController implements Serializable {
	private String name;
	private String sir = "testytestaa";

	public String getName(String name) {
		return name + "momodd";
	}

	public void setName(String name) {
		//logger.info(name);
		this.name = name + "MMx";
	}

	public void onPress() {
		//logger.info("Pickle pressed the button");
	}
}
