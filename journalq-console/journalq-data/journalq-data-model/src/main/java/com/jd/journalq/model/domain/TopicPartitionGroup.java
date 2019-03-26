package com.jd.journalq.model.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class TopicPartitionGroup implements Serializable {
    private String id;
    private Namespace namespace;
    private Topic topic;
    private int groupNo;
    private String partitions;
    private Integer electType;
    private Integer partitionCount;

    private transient Integer leader;
    private transient Integer recLeader;
    private transient Integer term;
    private transient Set<PartitionGroupReplica> replicaGroups = new TreeSet<>();
    private transient Set<Integer> learners;
    private transient Set<Integer> partitionSet = new TreeSet<>();
    private transient Set<Integer> isr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public String getPartitions() {
        return partitions;
    }

    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    public Integer getElectType() {
        return electType;
    }

    public void setElectType(Integer electType) {
        this.electType = electType;
    }

    public Integer getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(Integer partitionCount) {
        this.partitionCount = partitionCount;
    }

    @Transient
    public Set<Integer> getPartitionSet() {
        return partitionSet;
    }

    public Set<PartitionGroupReplica> getReplicaGroups() {
        return replicaGroups;
    }

    public void setReplicaGroups(Set<PartitionGroupReplica> replicaGroups) {
        this.replicaGroups = replicaGroups;
    }

    public void setPartitionSet(Set<Integer> partitionSet) {
        this.partitionSet = partitionSet;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public Integer getRecLeader() {
        return recLeader;
    }

    public void setRecLeader(Integer recLeader) {
        this.recLeader = recLeader;
    }

    public void addPartition(int partition){
        partitionSet.add(partition);
    }

    public Set<Integer> getLearners() {
        return learners;
    }

    public void setLearners(Set<Integer> learners) {
        this.learners = learners;
    }

    public Set<Integer> getIsr() {
        return isr;
    }

    public void setIsr(Set<Integer> isr) {
        this.isr = isr;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public enum ElectType{
        fix(1),
        raft(0);
        private int type;
        ElectType(int type){
            this.type = type;
        }
        public static ElectType valueOf(int type){
            switch (type) {
                case 0:
                    return raft;
                case 1:
                    return fix;
                default:
                    return fix;
            }
        }

        public int type() {
            return type;
        }
    }
}