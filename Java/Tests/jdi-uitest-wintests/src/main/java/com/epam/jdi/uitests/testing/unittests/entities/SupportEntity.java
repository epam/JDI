package com.epam.jdi.uitests.testing.unittests.entities;

import java.util.Objects;

public class SupportEntity {
    public String type;
    public String now;
    public String plans;

    public SupportEntity(){}

    public SupportEntity(String type, String now, String plans) {
        this.now = now;
        this.plans = plans;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupportEntity)) return false;
        SupportEntity that = (SupportEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(now, that.now) &&
                Objects.equals(plans, that.plans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, now, plans);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SupportEntity{");
        sb.append("now='").append(now).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", plans='").append(plans).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
