package org.fountanio.juancode.eng;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL10.alGenSources;

/**
 * Created by MicroCode on 6/10/2014.
 * <p></p>Stores a sound <p></p>
 * @see org.fountanio.juancode.eng.Engine
 */
public final class Sound {
    private int buffer;
    private int source;
    private String path;
    public Sound(String path) {
        this.path = path;
        WaveData data = null;
        try {
            data = WaveData.create(new BufferedInputStream(new FileInputStream(path)));
            buffer = AL10.alGenBuffers();
            alBufferData(buffer, data.format, data.data, data.samplerate);
            data.dispose();
            source = alGenSources();
            alSourcei(source, AL_BUFFER, buffer);
            buffer = AL10.alGenBuffers();
            AL10.alGenSources();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.append("SoundWave has not found: \"" + path + "\"");
            Display.destroy();
            AL.destroy();
            System.exit(1);
        }
    }

    public int buffer() {
        return buffer;
    }

    public int source() {
        return source;
    }

    public String path() { return path; }
}
