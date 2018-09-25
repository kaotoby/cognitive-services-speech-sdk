package com.microsoft.cognitiveservices.speech.audio;
//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//

import com.microsoft.cognitiveservices.speech.util.Contracts;
import com.microsoft.cognitiveservices.speech.SpeechConfig;

/**
  * Represents memory backed push audio input stream used for custom audio input configurations.
  */
public final class PushAudioInputStream extends com.microsoft.cognitiveservices.speech.audio.AudioInputStream
{
    // load the native library.
    static {
        // trigger loading of native library
        try {
            Class.forName(SpeechConfig.class.getName());
        }
        catch (ClassNotFoundException ex) {
            // ignored.
        }
    }

    /**
      * Creates a memory backed PushAudioInputStream using the default format (16Khz 16bit mono PCM).
      * @return The push audio input stream being created.
      */
    public static PushAudioInputStream create() {
        return new PushAudioInputStream(com.microsoft.cognitiveservices.speech.internal.AudioInputStream.CreatePushStream());
    }

    /**
      * Creates a memory backed PushAudioInputStream with the specified audio format.
      * @param format The audio data format in which audio will be written to the push audio stream's write() method (currently only support 16Khz 16bit mono PCM).
      * @return The push audio input stream being created.
      */
    public static PushAudioInputStream create(AudioStreamFormat format) {
        return new PushAudioInputStream(com.microsoft.cognitiveservices.speech.internal.AudioInputStream.CreatePushStream(format.getFormatImpl()));
    }

    /**
      * Writes the audio data specified by making an internal copy of the data.
      * @param dataBuffer The audio buffer of which this function will make a copy.
      */
    public void write(byte[] dataBuffer) {
        this._pushStreamImpl.Write(dataBuffer);
    }

    /**
      * Closes the stream.
      */
    public void close() {
        if (this._pushStreamImpl != null) {
            this._pushStreamImpl.Close();
        }
        this._pushStreamImpl = null;
    }

    protected PushAudioInputStream(com.microsoft.cognitiveservices.speech.internal.PushAudioInputStream stream) {
        super(stream);
        this._pushStreamImpl = stream;
    }

    private com.microsoft.cognitiveservices.speech.internal.PushAudioInputStream _pushStreamImpl;
}