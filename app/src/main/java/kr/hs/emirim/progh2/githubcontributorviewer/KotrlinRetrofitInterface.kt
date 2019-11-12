package kr.hs.emirim.progh2.githubcontributorviewer

import io.reactivex.Single
import retrofit2.http.GET;
import retrofit2.http.Path;

interface KotlinRetrofitInterface {
    @GET("repos/{owner}/{repo}/contributors")
    fun requestContributors(
        @Path("owner")owner:String,
        @Path("repo")repo:String
    ) : Single<Array<Contributors>>
}
