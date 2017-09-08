package com.epam.test_generator.entities;

public enum StepType {

    GIVEN {
        public String getStepType() {
            return "Given";
        }
    },
    WHEN {
        public String getStepType() {
            return "When";
        }
    },
    THEN {
        public String getStepType() {
            return "Then";
        }
    },
    AND {
        public String getStepType() {
            return "And";
        }
    },
    BUT {
        public String getStepType() {
            return "But";
        }
    },
    ANY {
        public String getStepType() {
            return "*";
        }
    };

    public abstract String getStepType();
}
