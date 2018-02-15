package ru.doom.wad.logic.format;

import ru.doom.wad.logic.task.LoadFileTask;
import ru.doom.wad.logic.format.wad.OpenWadTask;

/**
 * Represent file resource format tag.
 */
public enum Format {

    /**
     * Doom I/II WAD files.
     */
    WAD(OpenWadTask.class);

    private Class<? extends LoadFileTask> task;

    Format(final Class<? extends LoadFileTask> task) {
        this.task = task;
    }

    public Class<? extends LoadFileTask> getTask() {
        return task;
    }
}
