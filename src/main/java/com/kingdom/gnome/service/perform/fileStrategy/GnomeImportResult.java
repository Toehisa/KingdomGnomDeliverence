package com.kingdom.gnome.service.perform.fileStrategy;

import com.kingdom.gnome.dao.entity.Gnome;

import java.io.File;
import java.util.List;

public class GnomeImportResult {
    public enum Status {
        SUCCESS,
        PARTIAL,
        FILE_NOT_FOUND,
        EMPTY_DATA
    }

    private final Status status;
    private final List<Gnome> gnomes;
    private final int requestedCount;
    private final File file;

    private GnomeImportResult(Status status, List<Gnome> gnomes, int requestedCount, File file) {
        this.status = status;
        this.gnomes = gnomes;
        this.requestedCount = requestedCount;
        this.file = file;
    }

    public static GnomeImportResult success(List<Gnome> gnomes, int requestedCount, File file) {
        return new GnomeImportResult(Status.SUCCESS, gnomes, requestedCount, file);
    }

    public static GnomeImportResult partial(List<Gnome> gnomes, int requestedCount, File file) {
        return new GnomeImportResult(Status.PARTIAL, gnomes, requestedCount, file);
    }

    public static GnomeImportResult fileNotFound(int requestedCount, File file) {
        return new GnomeImportResult(Status.FILE_NOT_FOUND, null, requestedCount, file);
    }

    public static GnomeImportResult emptyData(int requestedCount, File file) {
        return new GnomeImportResult(Status.EMPTY_DATA, null, requestedCount, file);
    }

    public Status getStatus() { return status; }
    public List<Gnome> getGnomes() { return gnomes; }
    public int getRequestedCount() { return requestedCount; }
    public String getFilename() { return file.getName(); }
}
