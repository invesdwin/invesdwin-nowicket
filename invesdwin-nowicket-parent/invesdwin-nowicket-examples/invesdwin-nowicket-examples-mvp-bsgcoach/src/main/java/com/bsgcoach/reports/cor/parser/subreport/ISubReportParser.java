package com.bsgcoach.reports.cor.parser.subreport;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;

public interface ISubReportParser {

    void parse(FieldSet fieldSet, CompanyOperatingReports reports);

}
