package pl.kuba.managementapp.web.dataHolders;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class DataHolder {

    private String jobName;

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }
}
