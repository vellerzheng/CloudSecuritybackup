package com.mcloud.service.upload;

import java.util.List;

public interface MulCloudSDisposeService {
     List<String> getPartFilePath(String partFileDirectory);
    boolean uploadPartFileToClouds();
}
