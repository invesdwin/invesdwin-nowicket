package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.WarningLevel;

import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;

@NotThreadSafe
public class ConfiguredGoogleClosureJavaScriptCompressor extends GoogleClosureJavaScriptCompressor {

    public ConfiguredGoogleClosureJavaScriptCompressor() {
        super(CompilationLevel.SIMPLE_OPTIMIZATIONS);
    }

    public ConfiguredGoogleClosureJavaScriptCompressor(final CompilationLevel compilationLevel) {
        super(compilationLevel);
    }

    @Override
    protected void overrideOptions(final CompilerOptions options) {
        super.overrideOptions(options);
        WarningLevel.QUIET.setOptionsForWarningLevel(options);
        //http://stackoverflow.com/questions/13261325/how-can-i-set-the-language-in-option-for-the-closure-compiler
        options.setLanguageIn(LanguageMode.ECMASCRIPT5);
    }

}
