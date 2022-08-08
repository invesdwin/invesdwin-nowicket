/*
 * Copyright 2013~2015 Dan Haywood
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.invesdwin.nowicket.examples.isis.integration;

import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import org.apache.isis.applib.AppManifest;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.isisaddons.module.security.SecurityModule;

import de.invesdwin.util.collections.Arrays;
import de.invesdwin.util.collections.list.Lists;

@Immutable
public class SecurityAppManifest implements AppManifest {

    @Override
    public List<Class<?>> getModules() {
        return Arrays.asList(SecurityModule.class, SecurityAppModule.class);
    }

    @Override
    public List<Class<?>> getAdditionalServices() {
        return Lists.newArrayList(
                org.isisaddons.module.security.dom.password.PasswordEncryptionServiceUsingJBcrypt.class,
                org.isisaddons.module.security.dom.permission.PermissionsEvaluationServiceAllowBeatsVeto.class
        //,org.isisaddons.module.security.dom.permission.PermissionsEvaluationServiceVetoBeatsAllow.class
        );
    }

    @Override
    public String getAuthenticationMechanism() {
        return null;
    }

    @Override
    public String getAuthorizationMechanism() {
        return null;
    }

    @Override
    public List<Class<? extends FixtureScript>> getFixtures() {
        return null;
    }

    @Override
    public Map<String, String> getConfigurationProperties() {
        return null;
    }

}
