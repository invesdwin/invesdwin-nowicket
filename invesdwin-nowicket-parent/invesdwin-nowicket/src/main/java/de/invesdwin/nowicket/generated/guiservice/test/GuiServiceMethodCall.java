package de.invesdwin.nowicket.generated.guiservice.test;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.util.collections.Arrays;

@Immutable
public class GuiServiceMethodCall {
    private final GuiServiceMethod method;
    private final List<Object> args;

    GuiServiceMethodCall(final GuiServiceMethod method, final Object... args) {
        this.method = method;
        this.args = Arrays.asList(args);
    }

    public GuiServiceMethod getMethod() {
        return method;
    }

    public List<Object> getArgs() {
        return args;
    }
}