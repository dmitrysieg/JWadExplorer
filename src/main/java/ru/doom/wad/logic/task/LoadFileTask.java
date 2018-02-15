package ru.doom.wad.logic.task;

import java.io.File;

public abstract class LoadFileTask implements Runnable {

    public abstract void load(File file) throws Exception;

    public abstract void onError(Throwable e);

    private File file;

    public LoadFileTask withFile(final File file) {
        this.file = file;
        return this;
    }

    @Override
    public void run() {
        if (file != null) {
            try {
                load(file);
            } catch (Exception e) {
                onError(e);
            }
        }
    }
}
