package com.bsgcoach.reports;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.core.io.Resource;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class CsvItemReaderBuilder<T> {

    private String[] names;
    private FieldSetFactory fieldSetFactory;
    private String encoding;
    private String delimiter;
    private String quoteCharacter;
    private FieldSetMapper<T> fieldSetMapper;
    private Resource resource;
    private LineMapper<T> lineMapper;
    private Boolean strict;
    private RecordSeparatorPolicy recordSeparatorPolicy;

    public CsvItemReaderBuilder<T> withNames(final List<String> names) {
        this.names = names.toArray(new String[0]);
        return this;
    }

    public CsvItemReaderBuilder<T> withNames(final String[] names) {
        this.names = names.clone();
        return this;
    }

    public CsvItemReaderBuilder<T> withFieldSetFactory(final FieldSetFactory fieldSetFactory) {
        this.fieldSetFactory = fieldSetFactory;
        return this;
    }

    public CsvItemReaderBuilder<T> withEncoding(final String encoding) {
        this.encoding = encoding;
        return this;
    }

    public CsvItemReaderBuilder<T> withEncoding(final Charset encoding) {
        this.encoding = encoding.name();
        return this;
    }

    public CsvItemReaderBuilder<T> withDelimiter(final String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public CsvItemReaderBuilder<T> withFieldSetMapper(final FieldSetMapper<T> fieldSetMapper) {
        this.fieldSetMapper = fieldSetMapper;
        return this;
    }

    public CsvItemReaderBuilder<T> withResource(final Resource resource) {
        this.resource = resource;
        return this;
    }

    public CsvItemReaderBuilder<T> withQuoteCharacter(final String quoteCharacter) {
        this.quoteCharacter = quoteCharacter;
        return this;
    }

    public CsvItemReaderBuilder<T> withLineMapper(final LineMapper<T> lineMapper) {
        this.lineMapper = lineMapper;
        return this;
    }

    public CsvItemReaderBuilder<T> withStrict(final Boolean strict) {
        this.strict = strict;
        return this;
    }

    public CsvItemReaderBuilder<T> withRecordSeparatorPolicy(final RecordSeparatorPolicy recordSeparatorPolicy) {
        this.recordSeparatorPolicy = recordSeparatorPolicy;
        return this;
    }

    public FlatFileItemReader<T> get() {
        try {
            final FlatFileItemReader<T> items = new FlatFileItemReader<T>();
            if (resource != null) {
                items.setResource(resource);
            }
            if (encoding != null) {
                items.setEncoding(encoding);
            }
            items.setSaveState(false);
            if (lineMapper != null && recordSeparatorPolicy != null) {
                Assertions.assertThat(delimiter).isNull();
            }
            if (lineMapper != null) {
                Assertions.assertThat(names).isNull();
                Assertions.assertThat(fieldSetFactory).isNull();
                Assertions.assertThat(fieldSetMapper).isNull();
                Assertions.assertThat(strict).isNull();
                items.setLineMapper(lineMapper);
            } else {
                final DefaultLineMapper<T> lineMapper = newDefaultLineMapper();
                items.setLineMapper(lineMapper);
            }
            if (recordSeparatorPolicy != null) {
                Assertions.assertThat(quoteCharacter).isNull();
                items.setRecordSeparatorPolicy(recordSeparatorPolicy);
            } else {
                final CsvRecordSeparatorPolicy recordSeparatorPolicy = new CsvRecordSeparatorPolicy();
                if (quoteCharacter != null) {
                    recordSeparatorPolicy.setQuoteCharacter(quoteCharacter);
                }
                if (delimiter != null) {
                    recordSeparatorPolicy.setDelimiter(delimiter);
                }
                recordSeparatorPolicy.afterPropertiesSet();
                items.setRecordSeparatorPolicy(recordSeparatorPolicy);
            }
            items.afterPropertiesSet();
            return items;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DefaultLineMapper<T> newDefaultLineMapper() {
        final DefaultLineMapper<T> lineMapper = new DefaultLineMapper<T>();
        final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        if (delimiter != null) {
            tokenizer.setDelimiter(delimiter);
        }
        if (names != null) {
            tokenizer.setNames(names);
        }
        if (strict != null) {
            tokenizer.setStrict(strict);
        }
        if (fieldSetFactory != null) {
            tokenizer.setFieldSetFactory(fieldSetFactory);
        }
        lineMapper.setLineTokenizer(tokenizer);
        if (fieldSetMapper != null) {
            lineMapper.setFieldSetMapper(fieldSetMapper);
        }
        lineMapper.afterPropertiesSet();
        return lineMapper;
    }

}
