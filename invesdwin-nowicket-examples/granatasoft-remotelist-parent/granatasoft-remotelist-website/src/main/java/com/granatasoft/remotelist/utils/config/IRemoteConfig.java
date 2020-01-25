package com.granatasoft.remotelist.utils.config;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.IResource;

@NotThreadSafe
public interface IRemoteConfig {
    IResource getResource();

    String toJSON();
}
