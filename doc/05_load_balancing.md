# AFtheM - Backends - Load balancing

AFtheM has a simple load balancing capability we are going to discuss now. Before moving forward, make sure you have
read the [basic configuration guide](01_basic_configuration.md).

The backends in the `backends.yml` can alternatively be expressed as follows:

```yaml
- prefix: '[^/]*/upstreams'
  upstreams:
    urls:
    - 'https://server-1/endpoint'
    - 'https://server-2/endpoint'
    probe:
      count_up: 2
      count_down: 2
      method: GET
      timeout: 2 seconds
      path: ''
      status: 200
      interval: 10 seconds
  flow_id: default
```
In this configuration, `upstream` is replaced by the `upstreams` object.

`urls`: a list of URLs that will be used as upstreams

`probe`: the system will probe each URL periodically to make sure they are available. The probe is optional and, if
omitted, AFtheM will always considers URLs as functional.
* `path`: an extra path segment to be appeneded to the URL when probing
* `count_up`: how many times a probe should be successful before the URL can be considered as working
* `count_down`: how many times a probe should fail before the URL can be considered as non working
* `method`: which method, among GET/POST/PUT/PATCH needs to be used by the probe
* `timeout`: how long should a probe wait for a reply before it considers the URL as non responsive and therefore call
  it a failure
* `status`: the expected status code
* `interval`: how frequently should the probe run


## Notes

The system will look for a `probe` thread pool in the `implementers.yml` file. If no `probe` thread pool is defined,
default will be used. Please refer to the [fine tuning guide](06_fine_tuning.md) to learn more.