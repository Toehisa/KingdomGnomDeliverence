package com.kingdom.gnome.service.perform.fileStrategy;

import com.kingdom.gnome.dao.entity.Gnome;

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
    private final String filename;

    private GnomeImportResult(Status status, List<Gnome> gnomes, int requestedCount, String filename) {
        this.status = status;
        this.gnomes = gnomes;
        this.requestedCount = requestedCount;
        this.filename = filename;
    }

    public static GnomeImportResult success(List<Gnome> gnomes, int requestedCount, String filename) {
        return new GnomeImportResult(Status.SUCCESS, gnomes, requestedCount, filename);
    }

    public static GnomeImportResult partial(List<Gnome> gnomes, int requestedCount, String filename) {
        return new GnomeImportResult(Status.PARTIAL, gnomes, requestedCount, filename);
    }

    public static GnomeImportResult fileNotFound(int requestedCount, String filename) {
        return new GnomeImportResult(Status.FILE_NOT_FOUND, null, requestedCount, filename);
    }

    public static GnomeImportResult emptyData(int requestedCount, String filename) {
        return new GnomeImportResult(Status.EMPTY_DATA, null, requestedCount, filename);
    }

    public Status getStatus() { return status; }
    public List<Gnome> getGnomes() { return gnomes; }
    public int getRequestedCount() { return requestedCount; }
    public String getFilename() { return filename; }
}
