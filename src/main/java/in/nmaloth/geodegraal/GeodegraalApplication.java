package in.nmaloth.geodegraal;

import org.apache.geode.admin.internal.SystemMemberCacheEventProcessor;
import org.apache.geode.distributed.internal.DistributionAdvisor;
import org.apache.geode.distributed.internal.StartupResponseMessage;
import org.apache.geode.distributed.internal.StartupResponseWithVersionMessage;
import org.apache.geode.distributed.internal.locks.*;
import org.apache.geode.internal.cache.*;
import org.apache.geode.internal.cache.control.SerializableRegionRedundancyStatusImpl;
import org.apache.geode.internal.cache.ha.HARegionQueue;
import org.apache.geode.internal.cache.partitioned.PutMessage;
import org.apache.geode.internal.cache.partitioned.RemoveAllPRMessage;
import org.apache.geode.internal.cache.tx.RemoteClearMessage;
import org.apache.geode.internal.cache.tx.RemoteRemoveAllMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.*;


@TypeHints({
		@TypeHint(typeNames = "org.apache.geode.internal.cache.control.SerializableRegionRedundancyStatusImpl",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.control.SerializableRestoreRedundancyResultsImpl",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.FinalCheckPassedMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.NetworkPartitionMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.RemoveMemberMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.HeartbeatRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.HeartbeatMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.InstallViewMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.JoinRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.JoinResponseMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.LeaveRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.SuspectMembersMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.SuspectRequest",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.messages.ViewAckMessage",access = AccessBits.ALL),
        @TypeHint(typeNames = "org.apache.geode.management.internal.functions.CliFunctionResult",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.GMSMembershipView",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.locator.GetViewRequest",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.locator.GetViewResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.locator.FindCoordinatorRequest",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.gms.locator.FindCoordinatorResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tier.sockets.ClientTombstoneMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tx.RemoteClearMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tx.RemoteClearMessage$RemoteClearReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.WaitForViewInstallation",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.ha.HARegionQueue",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.ha.HARegionQueue$DispatchedAndCurrentEvents",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.membership.InternalDistributedMember",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateOperation",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateOperation$UpdateMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.ReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.DestroyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CreateRegionProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CreateRegionProcessor$CreateRegionMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CreateRegionProcessor$CreateRegionReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$QueryMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetWriteReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetWriteRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetLoadReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetLoadRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetSearchReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$NetSearchRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$ResponseMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.SearchLoadAndWriteProcessor$TryAgainException",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockRequestProcessor$DLockRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockRequestProcessor$DLockResponseMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockRequestProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockReleaseProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockReleaseProcessor$DLockReleaseMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DLockReleaseProcessor$DLockReleaseReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.admin.internal.SystemMemberCacheEventProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.admin.internal.SystemMemberCacheEventProcessor$SystemMemberCacheMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.cache.query.internal.CqEntry",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RegionStateMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$GIITestHook",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$FilterInfoMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$InterestMaps",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$InitialImageVersionedEntryList",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$Entry",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$ImageReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RequestSyncMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RVVReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RequestRVVProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RequestFilterInfoMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$FilterInfoProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$RequestImageMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$ImageProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InitialImageOperation$Status",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CloseCacheMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.NonGrantorDestroyedProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.NonGrantorDestroyedProcessor$NonGrantorDestroyedMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.NonGrantorDestroyedProcessor$NonGrantorDestroyedReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.GrantorRequestProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.GrantorRequestProcessor$GrantorInfoReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.GrantorRequestProcessor$GrantorRequestContext",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.GrantorRequestProcessor$GrantorRequestMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.ElderInitProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.ElderInitProcessor$ElderInitMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.ElderInitProcessor$ElderInitReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DeposeGrantorProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.locks.DeposeGrantorProcessor$DeposeGrantorMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.StartupMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.StartupResponseMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.StartupResponseWithVersionMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.ShutdownMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyRegionOperation",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyRegionOperation$DestroyRegionMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyRegionOperation$DestroyRegionWithContextMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.PutAllPRMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.RemoveAllPRMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.RemoveAllPRMessage$RemoveAllReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.RemoveAllPRMessage$RemoveAllResult",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.RemoveAllPRMessage$RemoveAllResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tx.RemoteRemoveAllMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tx.RemoteRemoveAllMessage$RemoveAllResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.tx.RemoteRemoveAllMessage$RemoveAllReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXCommitMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXCommitMessage$DistTXCommitReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXCommitMessage$DistTxCommitExceptionCollectingException",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXCommitMessage$DistTxCommitReplyProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXPrecommitMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXPrecommitMessage$DistTxPrecommitResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXPrecommitMessage$DistTxPrecommitExceptionCollectingException",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXPrecommitMessage$DistTxPrecommitReplyProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXPrecommitMessage$DistTXPrecommitReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage$DistTXRollbackReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage$DistTxRollbackExceptionCollectingException",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage$DistTXRollbackResponse",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DistTXRollbackMessage$DistTxRollbackReplyProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.partitioned.PutMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InvalidateOperation",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InvalidateOperation$InvalidateMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.InvalidateOperation$InvalidateWithContextMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyOperation",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyOperation$DestroyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.DestroyOperation$DestroyWithContextMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.DistributionAdvisor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.distributed.internal.DistributionAdvisor$Profile",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CacheDistributionAdvisor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CacheDistributionAdvisor$CacheProfile",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.CacheDistributionAdvisor$InitialImageAdvice",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.HARegion",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.HARegion$HARegionAdvisor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.HARegion$HARegionAdvisor$HAProfile",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.EntryEventImpl",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateAttributesProcessor",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateAttributesProcessor$UpdateAttributesMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateAttributesProcessor$ProfilesReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateAttributesProcessor$ProfileReplyMessage",access = AccessBits.ALL),
		@TypeHint(typeNames = "org.apache.geode.internal.cache.UpdateAttributesProcessor$UpdateAttributesReplyProcessor",access = AccessBits.ALL),





})
@SpringBootApplication
public class GeodegraalApplication {

	public static void main(String[] args) {

		SpringApplication.run(GeodegraalApplication.class, args);
	}


}
