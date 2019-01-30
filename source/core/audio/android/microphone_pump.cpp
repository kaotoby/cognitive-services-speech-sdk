//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//

#include "stdafx.h"

#include "microphone_pump.h"

namespace Microsoft {
namespace CognitiveServices {
namespace Speech {
namespace Impl {

using namespace std;

AUDIO_SETTINGS_HANDLE CSpxMicrophonePump::SetOptionsBeforeCreateAudioHandle()
{
    auto channels = GetChannelsFromConfig();
    if (channels > 0)
    {
        SPX_TRACE_VERBOSE("Android microphone only supports 1 or 2 channels.");
        SPX_IFTRUE_THROW_HR(channels != 1 && channels != 2, SPXERR_MIC_ERROR);
        SPX_TRACE_VERBOSE("The number of channel of microphone is set as %d", channels);
    }

    return CSpxMicrophonePumpBase::SetOptionsBeforeCreateAudioHandle();
}

} } } } // Microsoft::CognitiveServices::Speech::Impl
