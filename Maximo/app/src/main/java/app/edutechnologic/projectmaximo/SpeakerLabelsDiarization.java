package app.edutechnologic.projectmaximo;

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeakerLabel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechTimestamp;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class SpeakerLabelsDiarization {
    public static class RecoToken {
        private final Double startTime;
        private final Double endTime;
        private Integer speaker;
        private String word;
        private Boolean spLabelIsFinal;

        /**
         * Instantiates a new reco token based on timestamp
         *
         * @param speechTimestamp the speech timestamp
         */
        RecoToken(SpeechTimestamp speechTimestamp) {
            startTime = speechTimestamp.getStartTime();
            endTime = speechTimestamp.getEndTime();
            word = speechTimestamp.getWord();
        }

        /**
         * Instantiates a new reco token based on label
         *
         * @param speakerLabel the speaker label
         */
        RecoToken(SpeakerLabel speakerLabel) {
            startTime = speakerLabel.getFrom();
            endTime = speakerLabel.getTo();
            speaker = speakerLabel.getSpeaker();
        }

        /**
         * Get the word
         *
         * @param speechTimestamp the speech timestamp
         */
        void updateFrom(SpeechTimestamp speechTimestamp) {
            word = speechTimestamp.getWord();
        }

        /**
         * Get the speaker
         *
         * @param speakerLabel the speaker label
         */
        void updateFrom(SpeakerLabel speakerLabel) {
            speaker = speakerLabel.getSpeaker();
        }
    }


    public static class Utterance {
        private final Integer speaker;
        private String transcript = "";

        /**
         * Instantiates a new utterance
         *
         * @param speaker the speaker
         */
        public Utterance(int speaker) {
            this.speaker = speaker;
            this.transcript = "";
        }
    }

    public static class RecoTokens {

        private final Map<Double, RecoToken> recoTokenMap;

        /**
         * Instantiates a new reco tokens
         */
        public RecoTokens() {
            recoTokenMap = new LinkedHashMap<>();
        }

        /**
         * Adds the speech results to the entire sentence
         *
         * @param speechResults the speech results
         */
        public void add(SpeechResults speechResults) {
            if (speechResults.getResults() != null)
                for (int i = 0; i < speechResults.getResults().size(); i++) {
                    Transcript transcript = speechResults.getResults().get(i);
                    if (transcript.isFinal()) {
                        SpeechAlternative speechAlternative = transcript.getAlternatives().get(0);

                        for (int ts = 0; ts < speechAlternative.getTimestamps().size(); ts++) {
                            SpeechTimestamp speechTimestamp = speechAlternative.getTimestamps().get(ts);
                            add(speechTimestamp);
                        }
                    }
                }
            if (speechResults.getSpeakerLabels() != null)
                for (int i = 0; i < speechResults.getSpeakerLabels().size(); i++) {
                    add(speechResults.getSpeakerLabels().get(i));
                }

        }

        /**
         * Add the timestamp of the record token
         *
         * @param speechTimestamp the speech timestamp
         */
        public void add(SpeechTimestamp speechTimestamp) {
            RecoToken recoToken = recoTokenMap.get(speechTimestamp.getStartTime());
            if (recoToken == null) {
                recoToken = new RecoToken(speechTimestamp);
                recoTokenMap.put(speechTimestamp.getStartTime(), recoToken);
            } else {
                recoToken.updateFrom(speechTimestamp);
            }
        }

        /**
         * Add the label of the speaker
         *
         * @param speakerLabel the speaker label
         */
        public void add(SpeakerLabel speakerLabel) {
            RecoToken recoToken = recoTokenMap.get(speakerLabel.getFrom());
            if (recoToken == null) {
                recoToken = new RecoToken(speakerLabel);
                recoTokenMap.put(speakerLabel.getFrom(), recoToken);
            } else {
                recoToken.updateFrom(speakerLabel);
            }

            if (speakerLabel.isFinal()) {
                markTokensBeforeAsFinal(speakerLabel.getFrom());
                report();
                cleanFinal();
            }
        }

        private void markTokensBeforeAsFinal(Double from) {
            @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") Map<Double, RecoToken> recoTokenMap = new LinkedHashMap<>();

            for (RecoToken rt : recoTokenMap.values()) {
                if (rt.startTime <= from)
                    rt.spLabelIsFinal = true;
            }
        }

        public void report() {
            List<Utterance> uttterances = new ArrayList<>();
            Utterance currentUtterance = new Utterance(0);

            for (RecoToken rt : recoTokenMap.values()) {
                if (!currentUtterance.speaker.equals(rt.speaker)) {
                    uttterances.add(currentUtterance);
                    currentUtterance = new Utterance(rt.speaker);
                }
                currentUtterance.transcript = (new StringBuffer()).append(currentUtterance.transcript).append(rt.word).append(" ").toString();
            }
            uttterances.add(currentUtterance);

            String result = GsonSingleton.getGson().toJson(uttterances);
            System.out.println(result);
        }

        private void cleanFinal() {
            Set<Map.Entry<Double, RecoToken>> set = recoTokenMap.entrySet();
            for (Map.Entry<Double, RecoToken> e : set) {
                if (e.getValue().spLabelIsFinal) {
                    recoTokenMap.remove(e.getKey());
                }
            }
        }

    }

    private static CountDownLatch lock = new CountDownLatch(1);
}
